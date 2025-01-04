import { Exercise } from "./exercise.model";
import { Intensity } from "./intensity.model";

export class ActivityLogEntry{
    date: Date | null;
    exercise: Exercise | null;
    duration: number | null;
    weight: number | null;
    intensity: Intensity | null;

    constructor(date?: Date, exercise?: Exercise, duration?: number, weight?: number, intensity?: Intensity){
        this.date = date || null;
        this.exercise = exercise || null;
        this.duration = duration || null;
        this.weight = weight || null;
        this.intensity = intensity || null;
    }
}