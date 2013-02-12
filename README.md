# deveutils
Development utilities supporting development or deployment of Eucalyptus.

This repository is a collection of useful tools and documents meant to aide in work around Eucalyptus.  There are two kinds of "work" these tools and documents are meant to support:

1. **Development work**:  tools/docs for interacting with Eucalyptus in the course of implementation, testing, debugging, etc.
2. **Deployment work**:  tools/docs for managing the deployment environment in which Eucalyptus operates.

# Repository
This repository has the specific purpose of providing tools and docs _in support of_ other development or administrative activities.  The tools here are not an engineering exercise; they are not an end unto themselves.  With that in mind, the repository has some rules which are meant to:

1. Ensure the tools are self-contained and will work w/o needing to prepare the development or deployment environment.
2. Make it easy to rip out the piece you need _right now_, ship it to wherever, and expect it will work.
3. Keep tools _simple_ and _focused_ so they do not become a time sink of turd polishing.
4. Enable quick access to functionality with _trivial_ documentation.

# Rules
1. **Directories as Modules:** Every single tool or document lives in _its own directory_.  
2. **Flat Structure:** A tool/doc directory is an _atomic unit_, there are no subtools.  If there is a tool which you need to include a copy of in its entirety:  **do it!**
3. **Isolated:** Dependencies between tool directories are _prohibited_.
4. **Self-contained:** Every directory for a tool contains _everything_ which is needed to execute that tool w/in the tools.
5. **Self-describing:** Every directory _must_ have a meaningful name and contain an elaborating ``README.md`` where:

## Exceptions to being Self-contained 
Please indicate in the tool's ``README.md`` if the tool requires any of the following:

1. **Running Eucalyptus:**  Requiring a running Eucalyptus is _OK_.  
2. **Credentials:**  Requiring the presence of a ``eucarc`` file is _OK_.  
3. **euca2ools:**  Requiring the standard client tools is _OK_.  
4. **eucadmin tools:**  Requiring the standard admin tools is _OK_.  
5. **Eucalyptus binaries:**  Requiring Eucalyptus binaries at runtime is _OK_.
6. **Password-less ssh:**  Requiring ssh to the hosts running Eucalyptus is _OK_.
7. **Internet Access:** Requiring external interner access is _OK_.

## Disallowed by Self-contained 

1. **Package Installation:**  Do not assume you can change the host on which the tool is to be run, this compromises the value of the tool.

## Acid Tests

* From Rule #1: the only file that is ever in the root of the repo is this ``README.md``.  Everything else is a directory.
* From Rule #2: the root of the repository contains the same number of directories as there are tools/docs.
* From Rule #3: a tool will work (if it ever has) when executed from within the directory you ``rsync``d to your (least) favorite host.
* From Rule #4: a tool will work if ripped out of the repo on its own.
* From Rule #5: ``head -n1 */README.md`` will give a listing of the tool directories and a description of the contained tool.

## Intentionally neglected

1. Slimming down the repo.  Let it become many gigabytes.  Worry then.
