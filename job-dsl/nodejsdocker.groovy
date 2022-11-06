job('Node Jenkins Docker Deploy') {
    scm {
        git('https://github.com/skemuel007/jenkins-node-docker-demo.git') { node ->
            node / gitConfigName('skemuel007')
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
        dockerBuildAndPublish {
            repositoryName('skemuel007/nodejs-docker-jenkins')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}