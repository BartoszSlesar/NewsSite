import {News} from "@/models/news"
import Image from "next/image";
import newsLogoPlaceHolder from "@/assets/news-placeholder.png";
import {Clock1Icon} from "lucide-react";
import Badge from "./Badge";

interface NewsListItemProps {
  news: News;
}

export default function NewsListItem({
  news: {
      source,
      author,
      title,
      description,
      url,
      urlToImage,
      publishedAt,
      content
  },
}: NewsListItemProps) {
  return (
    <article className="flex gap-3 rounded-lg border p-5 hover:bg-muted/60">
      <Image
        src={urlToImage || newsLogoPlaceHolder}
        alt="news"
        width={100}
        height={100}
        className="self-center rounded-lg"
      />
      <div className="flex-grow space-y-3">
        <div>
          <h2 className="text-xl font-medium">{title}</h2>
          <p className="text-muted-foreground">{author}</p>
        </div>
      </div>
      <div className="hidden shrink-0 flex-col items-end justify-between sm:flex">
        <Badge>{source}</Badge>
        <span className="flex items-center gap-1.5 text-muted-foreground">
          {publishedAt}
        </span>
      </div>
    </article>
  );
}
