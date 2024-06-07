"use client";

import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import H1 from "@/components/ui/h1";
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {Input} from "@/components/ui/input";
import Select from "@/components/ui/select";
import {X} from "lucide-react";
import {Label} from "@/components/ui/label";
import RichTextEditor from "@/components/RichTextEditor";
import {draftToMarkdown} from "markdown-draft-js";
import LoadingButton from "@/components/LoadingButton";
import {error} from "console";
import {modifyArticle} from "./actions";
import {createArticleSchema, CreateArticleValues} from "@/lib/validation";
import {useEffect, useState} from "react";
import {getArticleById, test} from "@/api/newsApi";
import axios from "axios";
import {TextArea} from "@/components/ui/textarea";
import {News} from "@/models/news";

interface PageProps {
    articleId: string;
}


export default function NewJobForm(articleId: PageProps) {

    const [news, setArticle] = useState<News>({
        articleId: "-1",
        source: "",
        author: "",
        title: "",
        description: "",
        url: "",
        urlToImage: "",
        publishedAt: "",
        content: "",
    })



    const form = useForm<CreateArticleValues>({
        resolver: zodResolver(createArticleSchema),
        // TODO Here
        defaultValues:  async () => await getArticleById(articleId.articleId)
    });


    const {
        handleSubmit,
        control,
        setFocus,
        formState: {isSubmitting},
    } = form;


    async function onSubmit(values: CreateArticleValues) {
        const formData = new FormData();

        Object.entries(values).forEach(([key, value]) => {
            if (value) {
                formData.append(key, value);
            }
        });

        try {
            await modifyArticle(formData);
        } catch (error) {
            alert("Something went wrong, please try again.");
        }
    }

    return (
        <main className="m-auto my-10 max-w-3xl space-y-10">
            <div className="space-y-5 text-center">
                <H1>{news.title}</H1>
            </div>
            <div className="space-y-6 rounded-lg border p-4">
                <div>
                    <h2 className="font-semibold ">Article details</h2>
                </div>
                <Form {...form}>
                    <form
                        className="space-y-4 "
                        noValidate
                        onSubmit={handleSubmit(onSubmit)}

                    >
                        <FormField
                            control={control}
                            name="articleId"
                            render={({field}) => (
                                <FormItem>
                                    <FormControl>
                                        <Input type={"hidden"} {...field}  />
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />
                        <FormField
                            control={control}
                            name="title"
                            render={({field}) => (
                                <FormItem>
                                    <FormLabel>Article Title</FormLabel>
                                    <FormControl>
                                        <Input  {...field}
                                        />
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />


                        <FormField
                            control={control}
                            name="source"
                            render={({field}) => (
                                <FormItem>
                                    <FormLabel>Source</FormLabel>
                                    <FormControl>
                                        <Input {...field} />
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />
                        <FormField
                            control={control}
                            name="author"
                            render={({field}) => (
                                <FormItem>
                                    <FormLabel>Author</FormLabel>
                                    <FormControl>
                                        <Input {...field} />
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />

                        <FormField
                            control={control}
                            name="url"
                            render={({field}) => (
                                <FormItem>
                                    <FormLabel>Source Url</FormLabel>
                                    <FormControl>
                                        <Input {...field}/>
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />
                        <FormField
                            control={control}
                            name="urlToImage"
                            render={({field}) => (
                                <FormItem>
                                    <FormLabel>Source Url</FormLabel>
                                    <FormControl>
                                        <Input {...field} />
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />

                        <FormField
                            control={control}
                            name="description"
                            render={({field}) => (
                                <FormItem>
                                    <Label onClick={() => setFocus("description")}>
                                        Description
                                    </Label>
                                    <FormControl>
                                        <TextArea {...field} />
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />

                        <FormField
                            control={control}
                            name="content"
                            render={({field}) => (
                                <FormItem>
                                    <Label onClick={() => setFocus("content")}>
                                        Content
                                    </Label>
                                    <FormControl>
                                        <TextArea {...field}  />
                                    </FormControl>
                                    <FormMessage/>
                                </FormItem>
                            )}
                        />

                        <LoadingButton type="submit" loading={isSubmitting}>
                            Submit
                        </LoadingButton>
                    </form>
                </Form>
            </div>
        </main>
    );
}
