podifftrim
==========

podifftrim is a small utility that aims to make life slightly simpler for translators of
open source programs using the gettext .po system. Using pipes for input and output, it
is designed to take a unified diff as input, filter out "uninteresting" details, and give
a more clear and managable result as output.

So, what is interesting and what is not in this context? Let's take an example: You're a
translator on an open source project that lives on a git repository somewhere. You've
fetched the project's latest changes, updated your .po file, made your changes, or maybe
just a few changes, and now you'd like an overview of the changes in your .po file since
the last commit to the repo. Easy enough, a simple 'git diff' should take care of that.
But alas, often the diff is cluttered with lots and lots of info about strings that have
changed line numbers in the source code. This is info which is basically irrelevant to a
translator. Here's an excerpt of a real-life example:

    -#: ../src/develop/imageop.c:1180 ../src/libs/masks.c:1009
    +#: ../src/develop/imageop.c:1181 ../src/libs/masks.c:1009
     msgid "move up"
     msgstr "flyt op"
     
    -#: ../src/develop/imageop.c:1186 ../src/libs/masks.c:1012
    +#: ../src/develop/imageop.c:1187 ../src/libs/masks.c:1012
     msgid "move down"
     msgstr "flyt ned"

If you instead pipe your diff output to podifftrim, like this...

    git diff | podifftrim

...then lines like the above will get squelched, and you'll only get info that is
usable to you as a translator. Things like changed msgid and msgstr lines, fuzzy info,
etc.

Note, that the output of podifftrim is NOT in compliance with any diff syntax! It is
meant for human reading, only.

More Usage Examples
-------------------

Save the trimmed output from a git diff in a new text file:

    git diff | podifftrim > newfile

Feed podifftrim with an existing diff file, view the outout on-screen using the less tool:

    cat diff-file | podifftrim | less

Use podifftrim with Mercurial/hg, print everything on screen:

    hg diff | podifftrim

Use podifftrim with cvs (needs the -u parameter to get unified diff):

    cvs diff -u | podifftrim | less

See (almost) complete diff with interesting and uninteresting parts marked (for debugging):

    git diff | podifftrim -debug | less

