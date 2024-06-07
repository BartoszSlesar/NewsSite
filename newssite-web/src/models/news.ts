export interface News {
  articleId?: string
  source: string;
  author: string
  title: string
  description: string;
  url: string;
  urlToImage: string;
  publishedAt?: string;
  content: string;
}

export interface NewsDataSearchResponse {
  results: News[];
}
