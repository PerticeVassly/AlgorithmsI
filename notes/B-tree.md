Generalize 2-3 tree by allowing up to M-1 key-link pairs per node

At least 2 key-link pairs at root

At least M / 2 key-link pairs in other nodes

externel nodes contains client keys

Internal nodes contain copies of keys to guide search

A search or insertion in a B-tree of order M with N keys requires between log_{M-1}(N) and log_(M/2)(N) probes

All internel nodes (besides root) have between M / 2 and M - 1 links

Number of probes is at most 4

Always keep root page in memory

