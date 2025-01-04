export class Payment{
    userId: number | null;
    programId: number | null;
    method: string | null;
 

    constructor(userId?: number, programId?:number, method? : string){
        this.userId = userId || null;
        this.programId = programId || null;
        this.method = method || null;
    }
}