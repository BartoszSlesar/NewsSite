import {News} from "@/models/news";
import Image from "next/image";
import newsLogoPlaceHolder from "@/assets/news-placeholder.png";
import Markdown from "./Markdown";
import Link from "next/link";

interface ArticlePageProps {
    news: News;
}
// TODO edit that this will correctly be displayed
export default function CarPage({
                                    news: {
                                        articleId,
                                        source,
                                        author,
                                        title,
                                        description,
                                        url,
                                        urlToImage,
                                        publishedAt,
                                        content
                                    },
                                }: ArticlePageProps) {
    return (
        <section className="w-full grow space-y-5">
            <div className="flex items-center gap-3">
                {newsLogoPlaceHolder && (
                    <Image
                        src={urlToImage}
                        alt="News Logo"
                        width={100}
                        height={100}
                        className="rounded-xl"
                    />
                )}
                <div>
                    <div>
                        <h1 className="text-xl font-bold">{title}</h1>
                        <p className="font-semibold">
                            {url ? (
                                <Link
                                    href={new URL(url).origin}
                                    className="text-green-500 hover:underline"
                                >
                                    {source}
                                </Link>
                            ) : (
                                <span>{author}</span>
                            )}
                        </p>
                    </div>
                    <div className="text-sm text-muted-foreground">{description}</div>
                </div>
            </div>
            <div>{content && <Markdown>{content}</Markdown>}</div>
        </section>
    );
}
