insert into Portfolio (id, name) values (1, 'Portfolio1');
insert into Portfolio (id, name) values (2, 'Portfolio2');

insert into Stock (id, ticker, name, mean, stdDev) values (1, 'FB', 'Facebook Inc', -0.1, 0.1);
insert into Stock (id, ticker, name, mean, stdDev) values (2, 'AAPL', 'Apple Inc.', 0.2, 0.05);
insert into Stock (id, ticker, name, mean, stdDev) values (3, 'MSFT', 'Microsoft Corporation', 0.05, 0.01);
insert into Stock (id, ticker, name, mean, stdDev) values (4, 'ORCL', 'Oracle Corporation', 0.0, 0.2);
insert into Stock (id, ticker, name, mean, stdDev) values (5, 'GOOG', 'Google Inc', 0.03, 0.3);
insert into Stock (id, ticker, name, mean, stdDev) values (6, 'AMZN', 'Amazon.com, Inc.', 0.1, 0.2);
insert into Stock (id, ticker, name, mean, stdDev) values (7, 'NFLX', 'Netflix, Inc.', 0.2, 0.05);

insert into Position (portfolio_id, stock_id, quantity) values (1, 1, 165);
insert into Position (portfolio_id, stock_id, quantity) values (1, 2, 10);
insert into Position (portfolio_id, stock_id, quantity) values (1, 3, 140);
insert into Position (portfolio_id, stock_id, quantity) values (1, 4, 244);
insert into Position (portfolio_id, stock_id, quantity) values (1, 7, 136);
insert into Position (portfolio_id, stock_id, quantity) values (2, 2, 16);
insert into Position (portfolio_id, stock_id, quantity) values (2, 3, 230);
insert into Position (portfolio_id, stock_id, quantity) values (2, 5, 14);
insert into Position (portfolio_id, stock_id, quantity) values (2, 6, 54);
insert into Position (portfolio_id, stock_id, quantity) values (2, 7, 155);

insert into Price (stock_id, value) values (1, 31.91);
insert into Price (stock_id, value) values (2, 562.29);
insert into Price (stock_id, value) values (3, 29.06);
insert into Price (stock_id, value) values (4, 26.14);
insert into Price (stock_id, value) values (5, 591.53);
insert into Price (stock_id, value) values (6, 212.89);
insert into Price (stock_id, value) values (7, 70.22);