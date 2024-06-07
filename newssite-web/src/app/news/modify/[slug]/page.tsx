import NewJobForm from "@/app/news/modify/[slug]/ArticleModifyForm";

interface PageProps {
    params: { slug: string };
}

export default async function Page({params: {slug}}: PageProps) {
    return <NewJobForm articleId={slug}/>;
}