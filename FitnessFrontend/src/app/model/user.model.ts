export class User{
    id: number | null;
    name: string | null;
    surname: string | null;
    username: string | null;
    password: string | null;
    city: string | null;
    email: string | null;
    avatar: File | null;

    constructor(id?: number, name?: string, surname?:string, username?:string, password?:string, city?:string, email?:string,avatar?: File){
        this.id = id || null;
        this.name = name || null;
        this.surname = surname || null;
        this.username = username || null;
        this.password = password || null;
        this.city = city || null;
        this.email = email || null;
        this.avatar = avatar || null;
    }
}