import Link from "next/link";
import {News} from "@/models/news";
import {getNewsByQuery} from "@/api/newsApi";
import {ArrowLeft, ArrowRight} from "lucide-react";
import NewsListItem from "@/components/NewsListItem";
import {cn} from "@/lib/utils";
import React from "react";


interface NewsResultsProps {
    page?: number;
}


export default async function NewsResults({
                                              page = 1,
                                          }: NewsResultsProps) {

    // const date = new Date().toJSON().slice(0, 10);
    const date = "2024-05-16";
    const queryParams = new URLSearchParams()
    const limit = 10;
    queryParams.append("page", page.toString())
    queryParams.append("limit", limit.toString())
    queryParams.append("date", date)
    const news = await getNewsByQuery("?".concat(queryParams.toString()));


    return (
        <div className="grow space-y-4">
            {news.results.map((news: News) => (
                <Link key={news.articleId} href={`/cars/${news.articleId}`} className="block">
                    <NewsListItem news={news}/>
                </Link>
            ))}
            {news.results.length === 0 && (
                <p className="m-auto text-center">
                    No News was Found.
                </p>
            )}
            {news.results.length > 0 && (
                <Pagination currentPage={page} totalPages={Math.ceil(news.totalPages / limit)}/>
            )}
        </div>
    );
}


interface PaginationProps {
    currentPage: number,
    totalPages: number,

}

function Pagination({currentPage, totalPages}: PaginationProps) {
    function generatePageLink(page: number) {
        const queryParams = new URLSearchParams({
            page: page.toString()
        })

        return `/?${queryParams.toString()}`
    }


    return (
        <div className="flex justify-between">
            <Link
                href={generatePageLink(currentPage - 1)}
                className={cn(
                    "flex items-center gap-2 font-semibold",
                    currentPage <= 1 && "invisible",
                )}
            >
                <ArrowLeft size={16}/>
                Previous Page
            </Link>
            <span className="font-semibold ">
        Page {currentPage} of {totalPages}
      </span>
            <Link
                href={generatePageLink(currentPage + 1)}
                className={cn(
                    "flex items-center gap-2 font-semibold",
                    currentPage >= totalPages && "invisible",
                )}
            >
                Next Page
                <ArrowRight size={16}/>
            </Link>
        </div>
    );
}


