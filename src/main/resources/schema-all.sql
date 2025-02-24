DROP TABLE IF EXISTS bank_transactions;

CREATE TABLE bank_transactions
(
    id                 varchar          not null primary key,
    transaction_id     varchar          not null,
    transaction_date   timestamp        not null,
    montant            double precision not null,
    type               varchar          not null,
    compte_source      varchar          not null,
    compte_destination varchar          not null
)
