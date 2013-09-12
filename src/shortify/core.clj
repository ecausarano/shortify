(ns shortify.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [compojure.handler :as handler]
            [compojure.core :refer (GET POST defroutes)]
            [net.cgrand.enlive-html :as en]
            [compojure.route :as route]))

(defonce counter (atom 100))

(defonce urls (atom {}))

(defn shorten [url]
  (let [id (swap! counter inc)
        id (Long/toString id 36)]
    (swap! urls assoc id url)
    id))

(en/deftemplate homepage
  (en/xml-resource "homepage.html")
  [request]
  [:#listing :li] (en/clone-for [[id url] @urls]
                    [:a] (comp
                           (en/content (format "%s : %s" id url))
                           (en/set-attr :href (str \/ id)))))

(defn redirect [id]
  (response/redirect (@urls id)))

(defroutes app*
  (route/resources "/")
  (GET "/" request (homepage request))
  (GET "/:id" [id] (redirect id))
  (POST "/shorten" request
    (let [id (shorten (-> request :params :url))]
      (response/redirect "/"))))

(def app (handler/site app*))