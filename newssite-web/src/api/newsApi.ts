import api from "./api"
import {News} from "@/models/news"


export function getArticleById(articleId: string,config={}) {
    return api.get(`/api/v1/news/${articleId}`,config).then((res: any) => res.data);
};

export async function test(articleId: string){
    return  api.get(
        `/api/v1/news/${articleId}`
    ).then((res: any) => {return res.data})
}
export function getNewsByQuery(newsQuery: string) {
    console.log(newsQuery)
    return api.get(`api/v1/news${newsQuery}`).then((res: any) => res.data);


};


export function modifyArticleById(article: News) {
    return api.put('api/v1/news', article, {});
}

export function deleteArticleById(articleId: string) {
    return api.delete(`api/v1/news/${articleId}`, {});
}

