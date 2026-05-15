# booking_app

A booking system for professionals — tutors, coaches, instructors, that kind of thing — to offer lesson slots that their students can browse and book. The goal is simple: make it easy for a solo practitioner to manage their availability and for students to get on the calendar without a back-and-forth.

Full technical docs (stack, setup, architecture) are coming once there's actually code to document.

## What this project is really about

The booking system is the vehicle, not the destination.

This project exists to explore agentic development: what does it actually look like to build software using AI agents as first-class contributors? That means experimenting with multi-agent workflows, figuring out where agents help and where they get in the way, and building up a sense of what "agentic development" means in practice — not in theory.

The code will be real and the product will be functional, but the main thing being tested here is the process.

## Agentic Workflow

This project uses a multi-agent workflow powered by Claude Code.
See [docs/agents/README.md](docs/agents/README.md) for how it works.

## Getting Started

**Prerequisites:** Java 17+

```bash
make build    # compile the project
make test     # compile and run the test suite
make lint     # check formatting (ktlint)
make format   # auto-fix formatting issues
make run      # start the app locally on port 7070
make clean    # delete build outputs
```

## AI Involvement

This project uses Claude Code for implementation. All code is reviewed
by a human. The human is responsible for everything in this repo.
