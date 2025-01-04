export class Message{
    senderId: number | null;
    receiverId: number | null;
    receiverUsername: string | null;
    content: string | null;
    sentAt: number | null;

 
    constructor(senderId?: number, receiverId?: number, receiverUsername?: string, content?: string, sentAt?: number){
        this.senderId = senderId || null;
        this.receiverId = receiverId || null;
        this.receiverUsername = receiverUsername || null;
        this.content = content || null;
        this.sentAt = sentAt || null;
    }
}