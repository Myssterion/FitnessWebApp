export class Comment{
    username: string | null;
    content: string | null;
    userId:  number | null;
    programId: number | null;
    posted: number | null;

 
    constructor(username?: string, content?: string, programId?: number, userId?: number, posted?: number){
        this.username = username || null;
        this.content = content || null;
        this.programId = programId || null;
        this.userId = userId || null;
        this.posted = posted || null;
    }
}