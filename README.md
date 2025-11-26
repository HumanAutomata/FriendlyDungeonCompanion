A DnD helper app, for those who want to use pen and paper, but also want to conveniences of software.

To compile and run:

First go to the root directory ./FriendlyDungeonCompanion

and then run

Hold Ctrl and left click while edit box is checked to add a POI.

```sh
javac -cp "lib/*:src" -d bin $(find src -name "*.java"); java -cp "lib/*:bin" app.Main
```

or in Windows Powershell:

```sh
javac -cp "lib/*;src" -d bin (Get-ChildItem -Recurse -Filter *.java -Path src | Select-Object -ExpandProperty FullName); java -cp "lib/*;bin" app.Main
```
