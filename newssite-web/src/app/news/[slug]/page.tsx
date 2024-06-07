// import CarPage from "@/components/CarPage";
import {Button} from "@/components/ui/button";
import {notFound} from "next/navigation";
import React, {cache} from "react";
import {deleteArticleById, getArticleById, getNewsByQuery} from "@/api/newsApi";
import ArticleDeleteButton from "@/app/news/[slug]/ArticleDeleteButton";
import NewsPage from "@/components/NewsPage"
import Link from "next/link";

interface PageProps {
    params: { slug: string };
}

const getArticle = cache(async (slug: string) => {
    const article = await getArticleById(slug);

    if (!article) {
        notFound();
    }

    return article;
});




async function removeArticle(articleId: any) {
    await deleteArticleById(articleId)
}

export default async function Page({params: {slug}}: PageProps) {
    const news = await getArticle(slug);

    return (
        <main className="m-auto my-10 flex max-w-5xl flex-col items-center gap-5 px-3 md:flex-row md:items-start">
            <NewsPage news={news}/>
            <aside>
                <div className="m-auto gap-2 flex-col items-center px-3 md:flex-row md:space-y-2">

                    <Button asChild>
                        <a href="/" className="w-40 md:w-fit">
                            Back
                        </a>
                    </Button>
                    <ArticleDeleteButton news={news}/>
                    <Button className="bg-green-500" asChild>
                        <Link href={`/news/modify/${news.articleId}`}>Modify</Link>
                    </Button>

                </div>

            </aside>
        </main>
    );
}
