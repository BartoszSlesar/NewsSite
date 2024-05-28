import api from "./api"
import {News} from "@/models/news"

const baseUrl = {
    baseURL: process.env.REACT_APP_BASE_URL
};


export function getNewsById(articleId: number) {
    return api.get(`api/v1/news/${articleId}`).then((res: any) => res.data);
};


export function getNewsByQuery(newsQuery: string) {
    console.log(newsQuery)
    return api.get(`api/v1/news${newsQuery}`).then((res: any) => res.data);


};


export function modifyArticle(article: News) {
    return api.put('api/v1/news', article, {});
}

export function deleteArticleById(articleId: number) {
    return api.delete(`api/v1/news/${articleId}`, {});
}

