import Image from "next/image";
import H1 from "@/components/ui/h1";
import NewsResults from "@/components/NewsResults";
import {newsFilterValues} from "@/lib/validation";

interface PageProps {
    // next js special case, it took url and make it as search params
    searchParams: {
        page?: string;
    };
}

export default function Home({
                                 searchParams: {page},
                             }: PageProps) {



    return (
        <main className="m-auto my-10 max-w-5xl space-y-10 px-3">
            <div className="space-y-5 text-center">
                <H1>News Site</H1>
                <p className="text-muted-foreground">Daily news</p>
            </div>
            <section className="flex flex-col gap-4 md:flex-row">
                <NewsResults
                    page={page ? parseInt(page) : undefined}
                />
            </section>
        </main>
    );
}
