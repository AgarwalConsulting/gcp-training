# GCP Advanced Training by @algogrit

## Pre-requisites

- At least one year of active programming experience in: Java & Go
- Understanding of Client/Server architecture
- Working knowledge with SQL-based systems
- Some experience with DevOps: Deployment & Management
- Strong Familiarity with Linux
- Passing familiarity with tools:
  - Docker
  - Kubernetes
  - Hadoop
  - Spark
- Passing familiarity with concepts:
  - Cloud Networking (VPC & Subnets)
  - Cloud Access Management (IAM roles)

## Day 1

- Understanding Compute: Bare Metal vs Cloud Native
  - Why containerization?
  - Underpinnings of Docker
  - Underpinnings of Kubernetes

- Introduction to Distributed Systems
  - Underpinnings and the need for scalable systems
  - Trouble with being distributed
  - CAP Theorem

- Looking at the cloud offerings
  - Cloud Functions vs Compute engine
  - Data Flow vs Data Proc
  - Big Table vs Big Query

- Going Serverless
  - Overview of writing cloud functions
  - Functions Framework
    - Cloud Functions
    - Cloud Run
    - Knative-based environments

## Day 2

- Types of functions
  - HTTP Functions
  - Background Functions
  - CloudEvent Functions

- Writing your functions in Java walk-through
  - Frameworks
  - Logging
  - Deploying
  - JVM Languages

- Writing your functions in Go walk-through
  - Execution environment
  - Source code structure
  - Specifying dependencies
  - One-time initialization

- Overview of Go's concurrency
  - Understanding `context.Context` package

## Day 3

- Running cloud functions everywhere
  - Event-based architectures
  - Data Processing
  - Cloud Automation
  - Cloud Functions vs Cloud Run

- Working with functions on a day-to-day basis
  - Local Development
  - Deploy
    - From Local, SCM or Console
  - Build
  - Trigger
    - HTTP Triggers
    - PubSub Trigger
    - Cloud Storage Trigger
  - Test

- Advanced Functions
  - Networking
  - Secure
  - Logging and Monitoring

- Configurations
  - Scaling Behavior
  - Using Environment Variables

## Day 4

- Dealing with Data
  - OLTP vs OLAP
  - Data Mart vs Data Lake
  - Batch Processing vs Stream Processing
  - SQL vs NoSQL
    - Key-Value stores
    - Columnar databases
    - Graph databases

- Integrating with Cloud Databases
  - Cloud SQL
  - Cloud Spanner
  - Firestore

- Storage of Data
  - Cloud SQL vs Cloud spanner
  - Big Table overview
    - Connecting with cloud functions
  - Big Query overview
    - Connecting with cloud functions

## Day 5

- Streams of Data
  - Working with Google Pub/Sub
  - Architecture Overview
    - Scalability
    - Availability
    - Latency
  - Best Practices
    - Replaying and discarding messages
    - Ordering messages
    - Testing apps locally
    - Authentication & Access Control
    - Monitoring
    - Working with audit logs

- Optimizing Cloud Functions
  - Cold vs Warm starts
  - Latency and Networks
  - Compute time

- Best-practices
  - Tips & Tricks
  - Retrying Event-Driven Functions
  - Optimizing Networking

- Why choose Go over Java?
  - Start-up time
  - Memory Foot-print
    - GC Pauses
