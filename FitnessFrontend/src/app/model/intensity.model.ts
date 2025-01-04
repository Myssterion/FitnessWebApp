export class Intensity{
    id: number | null;
    intensityName: string | null;
 
    constructor(id?: number, intensityName?: string){
        this.id = id || null;
        this.intensityName = intensityName || null;
    }
}