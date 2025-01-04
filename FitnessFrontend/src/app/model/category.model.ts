export class Category{
    id: number | null;
    categoryName: string | null;
 
    constructor(id?: number, categoryName?: string){
        this.id = id || null;
        this.categoryName = categoryName || null;
    }
}