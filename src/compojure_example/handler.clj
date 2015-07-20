(ns compojure-example.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [cheshire.core :as json]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn json-response [data & [status]]
  {:status (or status 200)
  :headers {"Content-Type" "application/json; charset=utf-8"}
  :body (json/generate-string data)})

(defroutes app-routes
  (GET "/" [] "Hello World")
  (context "/api" []
           (GET "/" request
            (json-response {:greeting (str "Greetings " )})))


  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
