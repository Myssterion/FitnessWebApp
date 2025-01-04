export class Difficulty{
    id: number | null;
    difficulty: string | null;
 
    constructor(id?: number, difficulty?: string){
        this.id = id || null;
        this.difficulty = difficulty || null;
    }
}