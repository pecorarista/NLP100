# 言語処理100本ノック

This is an implementation of
<a href="http://www.cl.ecei.tohoku.ac.jp/nlp100/" target="_blank">言語処理100本ノック</a>
written in Scala.

To run a code, install sbt and type the following command.
```
sbt "run-main nlp100.chapter##.Q##"
```

You can create an environment for this code using
<a href="//docs.ansible.com/ansible/intro_installation.html" target="_blank">Ansible</a>.
Run `ansible-playbook site.yml` on RHEL7.

The 69th question requires a web application.
I created one in the directory `Q69`.
To run it, please have a look at `Q69/README.md`.
It will look like this.

<p style="text-align: center;">
<img src="./screenshots/webapp.png"/ style="width: 90%;">
</p>
