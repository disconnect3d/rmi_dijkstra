# Java RMI Dijkstra's graph search algorithm

Yet another Dijkstra graph search algorithm implemented using Java RMI to distribute work that can be parallelized...

Written for parallel and distributed systems course on AGH University of Science and Technology.

# Running

To compile everything just fire `make`.

To run server fire:

```
make runserver REGISTRY_IP=127.0.0.1 PORTS="9991 9992 9993"
```

To run client fire:
```
make runclient REGISTRY_IP=127.0.0.1 PORTS="9991 9992 9993" TESTCASE=1
```

Of course you can set proper parameters (`REGISTY_IP`, `PORTS` and `TESTCASE`).


# Directories

* **Client** - contains client implementation

* **Server** - contains server implementation

* **Shared** - contains stuff that is shared in Client & Server

* **testcases** - contains map examples

