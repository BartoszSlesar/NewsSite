"use server";

import {createArticleSchema} from "@/lib/validation";
import {redirect} from "next/navigation";
import {modifyArticleById} from "@/api/newsApi";




export async function modifyArticle(formData: FormData) {
    const values = Object.fromEntries(formData.entries());


    let {
        articleId,
        source,
        author,
        title,
        description,
        url,
        urlToImage,
        content

    } = createArticleSchema.parse(values);

    modifyArticleById({
        articleId,
        source,
        author,
        title,
        description,
        url,
        urlToImage,
        content
    })


    redirect("/article-modified");
}
