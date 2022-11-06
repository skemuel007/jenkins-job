job('Nodejs Deployment') {
    scm {
        git('git://github.com:skemuel007/jenkins-node-docker-demo.git') { node ->
            node / gitConfigName('DSL User')
            node / gitConfigEmail('skemuel007@gmail.com')
        }
    }
    // how many times the could will build
    triggers {
        scm('H/5 * * * *')
    }

    // the base framework
    wrappers {
        nodejs('nodejs')
        // this is the name of the Nodejs installation in Manage Jenkins -> Configure Tools -> Nodejs Installations
    }

    steps {
        shell("npm install")
    }

}