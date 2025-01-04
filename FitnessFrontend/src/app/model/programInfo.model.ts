import { SafeResourceUrl } from "@angular/platform-browser";

export class ProgramInfo{
    id: number | null;
    name: string | null;
    price: number | null;
    pictures: string[] | null;

    constructor(id?: number, name?: string, price?:number, pictures?:string[]){
        this.id = id || null;
        this.name = name || null;
        this.price = price || null;
        this.pictures = pictures || null;
    }
}