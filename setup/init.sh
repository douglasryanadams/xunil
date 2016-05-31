#!/bin/bash

if [ ! -f ~/.ssh/id_rsa ]; then
    ssh-keygen -t rsa -b 4096 -C "rook@xunil.io"
    echo "Upload to GitHub:"
    echo
    cat ~/.ssh/id_rsa.pub
    echo
fi

echo "Now run setup.sh"
