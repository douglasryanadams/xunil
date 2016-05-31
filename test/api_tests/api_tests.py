#!/usr/bin/python

import urllib2
import logging

logging.basicConfig(
    level=logging.INFO,
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
            'method': request.get_method(),
            'url': request.get_full_url(),
            'data': request.get_data(),
            'headers': request.headers
        }
        log.debug('Request:{}'.format(pr))
        try:
            response = self.test_opener.open(request)
        except urllib2.HTTPError as e:
            log.error('Failed Request ({})              : {} {}, because: {}'.format(e.code,pr['method'],pr['url'],e.reason))
            return dict(code=600)
        except urllib2.URLError as e:
            log.error('Failed Request ({})              : {} {}, because: {}'.format(e.code,pr['method'],pr['url'],e.reason))
            return dict(code=601)
        except httplib.HTTPException as e:
            log.error('Failed Request (HTTPException)   : {} {}'.format(pr['method'],pr['url']))
            return dict(code=602)
        except Exception as e:
            log.error('Failed Request                   : {} {}'.format(pr['method'],pr['url']))
            return dict(code=603)

        r_data = {
            'url': response.geturl(),
            'code': response.code,
            'headers': response.info().headers,
            'content': response.read()
        }
        log.debug('Response:{}'.format(r_data))
        log.info('Request Successful for            : {} {} ({})'.format(pr['method'],pr['url'],r_data['code']))
        return r_data

    def get(self, uri):
        test_request = urllib2.Request(
            url=self.base_url.format(uri),
            headers=self.headers
        )
        return self.__request(test_request)


if __name__ == '__main__':
    b = Browser('http://localhost:8080')

    b.get('')
    b.get('index.html')
    b.get('api/blog/1')

