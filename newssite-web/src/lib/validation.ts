import {z} from "zod";

const requiredString = z.string().min(2, "Required, Minimum length 2").max(50, "maximum length 50");
const numericRequiredString = requiredString.regex(/^\d+$/, "Must be a number");


export const createArticleSchema = z
    .object({
        source: requiredString,
        author: requiredString,
        title: requiredString,
        description: requiredString,
        url: requiredString,
        urlToImage: requiredString,
        content: requiredString,
    })

export type CreateArticleValues = z.infer<typeof createArticleSchema>;

export const newsFilterSchema = z.object({
    page: z.string().optional(),
});

export type newsFilterValues = z.infer<typeof newsFilterSchema>;
