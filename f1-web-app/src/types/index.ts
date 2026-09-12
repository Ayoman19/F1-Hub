export interface Race {
    id: number;
    name: string;
    date: string;
    location: string;
}

export interface Driver {
    id: number;
    name: string;
    team: string;
    points: number;
}

export interface Team {
    id: number;
    name: string;
    points: number;
}

export interface NewsArticle {
    id: number;
    title: string;
    content: string;
    date: string;
    source: string;
}