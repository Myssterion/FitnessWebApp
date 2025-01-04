export class Attribute{
    id: number | null;
    attributeName: string | null;
 
    constructor(id?: number, attributeName?: string){
        this.id = id || null;
        this.attributeName = attributeName || null;
    }
}