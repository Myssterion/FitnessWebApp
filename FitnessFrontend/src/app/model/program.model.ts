import { Attribute } from "./attribute.model";
import { Category } from "./category.model";
import { Difficulty } from "./difficulty.model";
import { UserInfo } from "./userInfo.model";

export class Program{
    id: number | null;
    name: string | null;
    description: string | null;
    duration: number | null;
    location: string | null;
    price: number | null;
    category: Category | null;
    attribute: Attribute | null;
    difficulty: Difficulty | null;
    instructor: UserInfo | null;
    video: string | null;
    pictures: string[] | null;
 
    constructor(id?: number, name?: string, price?:number, description?: string, duration?: number, location?: string, 
        category?: Category, attribute?: Attribute, difficulty?: Difficulty, instructor?: UserInfo, video?:string, pictures?: string[]){
        this.id = id || null;
        this.name = name || null;
        this.price = price || null;
        this.description = description || null;
        this.duration = duration || null;
        this.location = location || null;
        this.category = category || null;
        this.attribute = attribute || null;
        this.difficulty = difficulty || null;
        this.instructor = instructor || null;
        this.video = video || null;
        this.pictures = pictures || null;
    }
}