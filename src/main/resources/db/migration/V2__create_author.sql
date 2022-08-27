CREATE TABLE public.authors
(
    id bigserial NOT NULL,
    name VARCHAR(150) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    PRIMARY KEY (id)
);
