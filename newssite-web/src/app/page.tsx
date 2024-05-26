import Image from "next/image";
import H1 from "@/components/ui/h1";

export default function Home() {

  return (
      <main className="m-auto my-10 max-w-5xl space-y-10 px-3">
        <div className="space-y-5 text-center">
          <H1>Cars Data</H1>
          <p className="text-muted-foreground">Find your car.</p>
        </div>
        <section className="flex flex-col gap-4 md:flex-row">
         Section
        </section>
      </main>
  );
}
