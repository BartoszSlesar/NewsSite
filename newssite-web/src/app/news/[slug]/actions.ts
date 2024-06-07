"use server";

import { revalidatePath } from "next/cache";
import { redirect } from "next/navigation";
import {deleteArticleById, getArticleById} from "@/api/newsApi";

type FormState = { error?: string } | undefined;


export async function deleteArticle(
    prevState: FormState,
    formData: FormData,
): Promise<FormState> {
    try {

        const articleId = formData.get("articleId") as string;



        const news = getArticleById(articleId)


        await deleteArticleById(articleId)

        revalidatePath("/");
    } catch (error) {
        let message = "Unexpected error";
        if (error instanceof Error) {
            message = error.message;
        }
        return { error: message };
    }

    redirect("/");
}
