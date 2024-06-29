export interface Message {
    id?: number;
    chatName?: string;
    text: string;
    senderUsername: string;
    receiverUsername: string;
}