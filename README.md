# Ticketing System
## Layers

- models
- repo (repository interfaces + in-memory implementations)
- service (business logic)
- ui (console menu, input reader)
- main (application bootstrap)

## models
- Show
- TicketPurchase
- User
- Role (enum)

Each has:  
Fields (e.g. Show.id, Show.name, TicketPurchase.showId, …)  

Constructor with in-line validation (e.g. non-null fields, valid dates, positive quantities)  

Getters (and setters, if mutable)

## repo
Interfaces (e.g. ShowRepo, PurchaseRepo, UserRepo)  

CRUD operations (save, findById, findAll, maybe delete/update as needed)  

- In-Memory Implementations  
- InMemoryShowRepo  
- InMemoryPurchaseRepo
- InMemoryUserRepo
  
Backed by simple collections (Map<Id,Entity>), no persistence.

## service
- AuthService
  - Login, logout, role checks (uses UserRepo)
- ShowService
  - List shows, search, maybe add/update (uses ShowRepo)

- TicketService
  - Purchase flow: check availability, create TicketPurchase, decrement inventory, persist (uses ShowRepo + PurchaseRepo)

## ui
- InputReader
  - Wraps Scanner or similar, handles raw console input,validation
- ConsoleMenu
  - Displays menu based on user role, routes commands to services, loops until exit
- main
  - Wire everything up (instantiate repos → services → UI → start menu)
