import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiaryentryComponent } from './diaryentry.component';

describe('DiaryentryComponent', () => {
  let component: DiaryentryComponent;
  let fixture: ComponentFixture<DiaryentryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DiaryentryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DiaryentryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
