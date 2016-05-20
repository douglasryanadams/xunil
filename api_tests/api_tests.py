#!/usr/bin/python

import urllib2
import logging

logging.basicConfig(
    level=logging.DEBUG,
    format='%(levelname)5.5s : %(message)s',

)

log = logging.getLogger()


class Browser:
    def __init__(self, base_url, headers={}):
        self.base_url = base_url + "/{}"
        self.test_opener = urllib2.build_opener()
        self.headers = headers

    def __request(self, request):
        pr = {
            'url': request.get_full_url(),
            'data': request.get_data(),
            'headers': request.headers
        }
        log.debug('Request:{}'.format(pr))
        response = self.test_opener.open(request)
        r_data = {
            'url': response.geturl(),
            'code': response.code,
            'headers': response.info(),
            'content': response.read()
        }
        log.debug('Response:{}'.format(r_data))
        return r_data

    def get(self, uri):
        test_request = urllib2.Request(
            url=self.base_url.format(uri),
            headers=self.headers
        )
        return self.__request(test_request)


if __name__ == '__main__':
    b = Browser('http://localhost:8080')

    b.get('file')
    b.get('blog/12')
