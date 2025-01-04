import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import { Chart, registerables } from 'chart.js';
import { Exercise } from '../model/exercise.model';
import { GraphService } from './graph.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../service/auth.service';

Chart.register(...registerables);

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrl: './graph.component.css'
})
export class GraphComponent implements OnInit {

  monthsMap: Map<number, string>;
  selectedOption: string = 'year';
  selectedOptionDateInfo: string ;
  selectedExercise: Exercise | null = null;
  isActive: boolean = true;
  year: number;
  month: number;
  data: Map<Date, number> = new Map<Date, number>();

  @ViewChild('myChart', { static: true }) myChart!: ElementRef;
  chart!: Chart;

  exercises: Exercise[] = [];

  constructor(private graphService: GraphService, private authService: AuthService) { 
    this.year = new Date().getFullYear();
    this.month = new Date().getMonth() + 1;
    this.selectedOptionDateInfo = this.year.toString();
    this.monthsMap = new Map<number, string>([
      [0,'Januar'],
      [1,'Februar'],
      [2, 'Mart'],
      [3,'April'],
      [4,'May'],
      [5, 'Jun'],
      [6,'Jul'],
      [7,'Avgust'],
      [8, 'Septembar'],
      [9,'Oktobar'],
      [10,'Novembar'],
      [11, 'Decembar'],
    ]);
  }

  ngOnInit(): void {
    this.loadExercises();
    this.createChart();
  }

  setActive(option: string) {
    this.selectedOption = option;
    this.updateDateInfo();
    if(this.selectedExercise != null)
      this.loadDataForGraph(this.selectedExercise);
  }

  updateDateInfo() {
    if(this.selectedOption === 'all')
      this.isActive = false;
    else if(this.selectedOption === 'year') {
      this.selectedOptionDateInfo = this.year.toString();
      this.isActive = true;
    }
    else if(this.selectedOption === 'month') {
      this.selectedOptionDateInfo = this.monthsMap.get(this.month) + ' ' + this.year.toString();
      this.isActive = true;
    }
  }


  loadExercises(): void {
    this.graphService.getExercises(this.authService.getUserId()).subscribe(
      (response: Exercise[]) => {
        this.exercises = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  createChart(): void {
    this.chart = new Chart(this.myChart.nativeElement, {
      type: 'line', // or 'line', 'pie', etc.
      data: {
        labels: [],
        datasets: [
          {
            label: 'My First Dataset',
            data: [],
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(255, 99, 132, 0.2)'
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)',
              'rgba(255, 99, 132, 1)'
            ],
            borderWidth: 1,
            spanGaps: true
          }
        ]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true
          }
        },
        spanGaps: true,
        plugins: {
          legend: {
            display: false // Adjust as needed
          }
      }
      }
    });
  }

  onExerciseChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const selectedIndex = selectElement.selectedIndex;
    this.selectedExercise = this.exercises[selectedIndex];

    console.log('Selected Exercise ID:', this.selectedExercise);

    this.loadDataForGraph(this.selectedExercise);
    }


  loadDataForGraph(selectedExercise: Exercise) {
    if(this.selectedExercise != null && this.selectedExercise.id != null)
    this.graphService.getGraphData(this.authService.getUserId(),this.selectedExercise.id, this.selectedOption, this.year, this.month).subscribe(
      (response: {[key: string]: number }) => {
        this.data = this.processActivityLogEntries(response);
        this.updateGraph(this.data);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  processActivityLogEntries(entries: { [key: string]: number }): Map<Date, number> {
    const map = new Map<Date, number>();

    Object.entries(entries).forEach(([key, value]) => {
        const date = new Date(key); // Convert the string key to Date
        map.set(date, value);
    });

    return map;
}

updateGraph(data: Map<Date, number>) {
  if (this.chart) {
    let labels: string[] = [];
    let dataPoints: number[] = [];

    if (this.selectedOption === 'month') {
      labels = this.getDayLabels(this.year, this.month);
      const days = new Array(labels.length).fill(0);

      // Group by day in the selected month
      data.forEach((weight, date) => {
        if (date.getFullYear() === this.year && date.getMonth() + 1 === this.month) {
          const day = date.getDate() - 1; // Convert to zero-based index
          days[day] += weight;
        }
      });

      dataPoints = days.map(value => value === 0 ? null : value); 

    } else if (this.selectedOption === 'year') {
      labels = this.getMonthLabels();
      const months = new Array(labels.length).fill(0);

      // Group by month in the selected year
      data.forEach((weight, date) => {
        if (date.getFullYear() === this.year) {
          const month = date.getMonth(); // Zero-based index
          months[month] += weight;
        }
      });

      dataPoints = months;
    } else if(this.selectedOption === 'all') {
      const dates = Array.from(data.keys());
      const oldest = Math.min(...dates.map(date => date.getFullYear()));
      
      labels = this.getYearLabels(oldest); 
      const years = new Array(labels.length).fill(0);
     
      data.forEach((weight, date) => {
        const year = date.getFullYear();

        const yearIndex = labels.indexOf(year.toString());
        years[yearIndex] += weight;
       
      });

      dataPoints = years;
    }

    
    this.chart.data.labels = labels;
    this.chart.data.datasets[0].data = dataPoints;
    this.chart.data.datasets[0]
    
    this.chart.update(); // Refresh the chart with new data
  }
}
  getYearLabels(startingYear: number): string[] {
   const years: number[] = [];
   const currYear = new Date().getFullYear();

   if(currYear === startingYear)
    startingYear = startingYear - 5;
   for (let year = startingYear; year <= currYear; year++) {
    years.push(year);
  }
   return years.map(year => `${year}`);
  }

  getMonthLabels(): string[] {
    return [
      'January', 'February', 'March', 'April', 'May', 'June',
      'July', 'August', 'September', 'October', 'November', 'December'
    ];
  }
  
  getDayLabels(year: number, month: number): string[] {
    const daysInMonth = new Date(year, month, 0).getDate();
    return Array.from({ length: daysInMonth }, (_, i) => `${i + 1}`);
  }

  changeDate(changeTo: string) {
    if(this.selectedOption === 'year') {
      if(changeTo === 'after')
        this.year++;
      else if(changeTo === 'before')
        this.year--;
    } else if(this.selectedOption === 'month') {
        if(changeTo === 'after') {
          const temp = this.month + 1;
          if(temp === 12)
            this.year++;
          this.month++;
        }
        else if(changeTo === 'before') {
          const temp = this.month - 1;
          if(temp === -1) {
            this.year--;
            this.month = 12;
          }
          this.month--;
        }
    }
    this.updateDateInfo();
    if(this.selectedExercise != null)
      this.loadDataForGraph(this.selectedExercise);
    }
}
