create sequence if not exists booking_booking_seq
    start with 1
    increment by 1
    no minvalue
    no maxvalue
    cache 1;

create table booking_booking (
    id bigint not null primary key default nextval('booking_booking_seq'),
    flight_id bigint not null,
    user_id bigint not null,
    status varchar not null,
    created_at timestamp with time zone,
    updated_at timestamp with time zone
)