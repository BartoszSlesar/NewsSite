import {z} from "zod";

const requiredString = z.string().min(2, "Required, Minimum length 2").max(255, "maximum length 50");
const requiredText = z.string().min(2, "Required, Minimum length 2").max(5000, "maximum length 50");
const numericRequiredString = requiredString.regex(/^\d+$/, "Must be a number");


export const createArticleSchema = z
    .object({
        articleId: z.any(),
        source: requiredString,
        author: requiredString,
        title: requiredString,
        description: requiredText,
        url: requiredString,
        urlToImage: requiredString,
        content: requiredText,
    })

export type CreateArticleValues = z.infer<typeof createArticleSchema>;

export const newsFilterSchema = z.object({
    page: z.string().optional(),
});

export type newsFilterValues = z.infer<typeof newsFilterSchema>;
