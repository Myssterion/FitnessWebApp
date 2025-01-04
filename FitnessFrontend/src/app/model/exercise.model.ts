export class Exercise{
    id: number | null;
    exerciseName: string | null;
    muscle: string | null;
    type: string | null;
 
    constructor(id?: number, exerciseName?: string,  muscle?: string,  type?: string){
        this.id = id || null;
        this.exerciseName = exerciseName || null;
        this.muscle = muscle || null;
        this.type = type || null;
    }
}