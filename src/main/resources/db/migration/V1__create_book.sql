CREATE TABLE public.books
(
    id bigserial NOT NULL,
    title VARCHAR(50) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    PRIMARY KEY (id)
);
