export class Register{
    name: string | "";
    surname: string | "";
    username: string | "";
    password: string | "";
    confirmPassword: string |"";
    city: string | "";
    email: string | "";
    avatar: File | null;
 
    constructor(name?: string, surname?: string,  username?: string, password?: string,confirmPassword?: string, city?: string, email?: string,avatar?: File){
        this.name = name || "";
        this.surname = surname || "";
        this.username = username || "";
        this.city = city || "";
        this.password = password || "";
        this.confirmPassword = confirmPassword || "";
        this.email = email || "";
        this.avatar = avatar || null;
    }
}