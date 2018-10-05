create table if not exists (
	method txt not null unique,
	counter INTEGER NOT NULL DEFAULT 1
);
