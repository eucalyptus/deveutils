The checks which are there cover looking at the point of view of each hosts state for:
- group membership view state
- hostmap state
- service state
- database connections
- database data

To use the tool you will need the admin 'eucarc'.  You can run the tool anywhereThe checks which are there cover looking at the point of view of each hosts state for:
- group membership view state
- hostmap state
- service state
- database connections
- database data

To use the tool you will need the admin 'eucarc'.  You can run the tool anywhereThe checks which are there cover looking at the point of view of each hosts state for:
- group membership view state
- hostmap state
- service state
- database connections
- database data

To use the tool you will need the admin 'eucarc'.  You can run the tool anywhere.  The arguments to the tool are options which indicate which checks to perform and, optionally, the list of hosts against which to perform the checks.  If the host list is omitted, the list of hosts is determined based on the response of euca-describe-services for the system indicated by ${EC2URL}.

```bash
 survey-ha 17456 > ./euca-survey-ha -h
Usage: euca-survey-ha [OPTIONS] [HOST...]

 Survey the state of various Eucalyptus internals.

  -h|--help                      Show this help
  -q|--quick                     Run quick checks; hold no locks (currently include group membership, service state, and database connection state)
  -a|--all                       Run all the checks
  -g|--group-membership          Show the current group membership views for the hosts in the system
  -H|--hostmap                   Show the current host state including the status bits which indicate whether the system has bootstrapped, has a database and whether the database is synced
  -s|--services                  Check the current service state for discrepancies
  -d|--db-connections            Check the current database pools for discrepancies (e.g., deactivated connections for active hosts, dead connections to active host, etc.)
  -D|--db-data                   Check the consistency of database data at the scope of table size counts.
  -A|--all-db                    Run all the database checks.
  -S|--access-key-id             Access key (will default to the value of AWS_ACCESS_KEY)
  -I|--access-key-id             Secret key (will default to the value of AWS_SECRET_KEY)
 

 survey-ha 17456 > ./euca-survey-ha -h
Usage: euca-survey-ha [OPTIONS] [HOST...]
```
 Survey the state of various Eucalyptus internals.

  -h|--help                      Show this help
  -q|--quick                     Run quick checks; hold no locks (currently include group membership, service state, and database connection state)
  -a|--all                       Run all the checks
  -g|--group-membership          Show the current group membership views for the hosts in the system
  -H|--hostmap                   Show the current host state including the status bits which indicate whether the system has bootstrapped, has a database and whether the database is synced
  -s|--services                  Check the current service state for discrepancies
  -d|--db-connections            Check the current database pools for discrepancies (e.g., deactivated connections for active hosts, dead connections to active host, etc.)
  -D|--db-data                   Check the consistency of database data at the scope of table size counts.
  -A|--all-db                    Run all the database checks.
  -S|--access-key-id             Access key (will default to the value of AWS_ACCESS_KEY)
  -I|--access-key-id             Secret key (will default to the value of AWS_SECRET_KEY)
 

 survey-ha 17456 > ./euca-survey-ha -h
Usage: euca-survey-ha [OPTIONS] [HOST...]

 Survey the state of various Eucalyptus internals.

  -h|--help                      Show this help
  -q|--quick                     Run quick checks; hold no locks (currently include group membership, service state, and database connection state)
  -a|--all                       Run all the checks
  -g|--group-membership          Show the current group membership views for the hosts in the system
  -H|--hostmap                   Show the current host state including the status bits which indicate whether the system has bootstrapped, has a database and whether the database is synced
  -s|--services                  Check the current service state for discrepancies
  -d|--db-connections            Check the current database pools for discrepancies (e.g., deactivated connections for active hosts, dead connections to active host, etc.)
  -D|--db-data                   Check the consistency of database data at the scope of table size counts.
  -A|--all-db                    Run all the database checks.
  -S|--access-key-id             Access key (will default to the value of AWS_ACCESS_KEY)
  -I|--access-key-id             Secret key (will default to the value of AWS_SECRET_KEY)

