export class UserInfo{
    id: number | null;
    name: string | null;
    surname: string | null;

    constructor(id?: number, name?: string, surname?:string){
        this.id = id || null;
        this.name = name || null;
        this.surname = surname || null;
    }
}