"use client";

import FormSubmitButton from "@/components/FormSubmitButton";
import {useFormState} from "react-dom";
import {deleteArticle} from "./actions";
import {News} from "@/models/news";

interface NewsDataProps {
    news: News;
}

export default function ArticleDeleteButton({news}: NewsDataProps) {
    const [formState, formAction] = useFormState(deleteArticle, undefined);
    return (
        <form action={formAction} className="space-y-1">
            <input hidden name="articleId" value={news.articleId}/>
            <FormSubmitButton className="w-full bg-red-500 hover:bg-red-600">
                Delete
            </FormSubmitButton>
            {formState?.error && (
                <p className="text-sm text-red-500">{formState.error}</p>
            )}
        </form>
    );
}
